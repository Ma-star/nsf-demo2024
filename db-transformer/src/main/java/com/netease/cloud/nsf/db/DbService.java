package com.netease.cloud.nsf.db;

import com.netease.cloud.nsf.db.configportal.PortalAppMapper;
import com.netease.cloud.nsf.db.configportal.PortalAppNamespaceMapper;
import com.netease.cloud.nsf.db.configservice.*;
import com.netease.cloud.nsf.db.platform.PermissionScope;
import com.netease.cloud.nsf.db.platform.PermissionScopeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DbService {

    @Value("${env}")
    private String env;

    @Autowired
    private PermissionScopeMapper permissionScopeMapper;

    @Autowired
    private AppMapper appMapper;
    @Autowired
    private AppNamespaceMapper appNamespaceMapper;
    @Autowired
    private ClusterMapper clusterMapper;
    @Autowired
    private CommitMapper commitMapper;
    @Autowired
    private GrayReleaseRuleMapper grayReleaseRuleMapper;
    @Autowired
    private InstanceMapper instanceMapper;
    @Autowired
    private InstanceConfigMapper instanceConfigMapper;
    @Autowired
    private NamespaceMapper namespaceMapper;
    @Autowired
    private ReleaseMapper releaseMapper;
    @Autowired
    private ReleaseHistoryMapper releaseHistoryMapper;
    @Autowired
    private ReleaseMessageMapper releaseMessageMapper;

    @Autowired
    private PortalAppMapper portalAppMapper;
    @Autowired
    private PortalAppNamespaceMapper portalAppNamespaceMapper;
    @Autowired
    private com.netease.cloud.nsf.db.configportal.PermissionMapper permissionMapper;
    @Autowired
    private com.netease.cloud.nsf.db.configportal.RoleMapper roleMapper;

    private Map<String, PermissionScope> projectInfo = new HashMap<>();

    public void transformer() {
        
        appMapper.list().forEach(app -> {
            String newId = getNewAppId(app.AppId);
            app.AppId = newId;
            app.Name = newId;
            appMapper.update(app);
        });

        appNamespaceMapper.list().forEach(appNamespace -> {
            appNamespace.AppId = getNewAppId(appNamespace.AppId);
            if (appNamespace.Name.startsWith("CMN.")) {
                appNamespace.Name = getNewName(appNamespace.Name);
            }
            appNamespaceMapper.update(appNamespace);
        });

        clusterMapper.list().forEach(cluster -> {
            cluster.AppId = getNewAppId(cluster.AppId);
            clusterMapper.update(cluster);
        });

        commitMapper.list().forEach(commit -> {
            commit.AppId = getNewAppId(commit.AppId);
            commitMapper.update(commit);
        });

        grayReleaseRuleMapper.list().forEach(grayReleaseRule -> {
            grayReleaseRule.AppId = getNewAppId(grayReleaseRule.AppId);
            grayReleaseRuleMapper.update(grayReleaseRule);
        });

        instanceMapper.list().forEach(instance -> {
            instance.AppId = getNewAppId(instance.AppId);
            instanceMapper.update(instance);
        });

        instanceConfigMapper.list().forEach(instanceConfig -> {
            instanceConfig.ConfigAppId = getNewAppId(instanceConfig.ConfigAppId);
            instanceConfigMapper.update(instanceConfig);
        });

        namespaceMapper.list().forEach(namespace -> {
            namespace.AppId = getNewAppId(namespace.AppId);
            namespace.NamespaceName = getNewName(namespace.NamespaceName);
            namespaceMapper.update(namespace);
        });

        releaseMapper.list().forEach(release -> {
            release.AppId = getNewAppId(release.AppId);
            release.NamespaceName = getNewName(release.NamespaceName);
            releaseMapper.update(release);
        });

        releaseHistoryMapper.list().forEach(releaseHistory -> {
            releaseHistory.AppId = getNewAppId(releaseHistory.AppId);
            releaseHistory.NamespaceName = getNewName(releaseHistory.NamespaceName);
            releaseHistoryMapper.update(releaseHistory);
        });

        releaseMessageMapper.list().forEach(releaseMessage -> {
            releaseMessage.Message = getNewMessage(releaseMessage.Message);
            releaseMessageMapper.update(releaseMessage);
        });

        portalAppMapper.list().forEach(app -> {
            String newId = getNewAppId(app.AppId);
            app.AppId = newId;
            app.Name = newId;
            portalAppMapper.update(app);
        });

        portalAppNamespaceMapper.list().forEach(appNamespace -> {
            appNamespace.AppId = getNewAppId(appNamespace.AppId);
            appNamespace.Name = getNewName(appNamespace.Name);
            portalAppNamespaceMapper.update(appNamespace);
        });


        permissionMapper.list().forEach(permission -> {
            permission.TargetId = getNewPermissionTarget(permission.TargetId);
            permissionMapper.update(permission);
        });

        roleMapper.list().forEach(role -> {
            role.RoleName = getNewRoleName(role.RoleName);
            roleMapper.update(role);
        });

    }

    private String getNewAppId(String oldId) {
        String[] items = oldId.split("\\.");
        if (items.length != 2) {
            return oldId;
        }
        String projectid;
        String serviceName;
        boolean Public = false;
        if (oldId.startsWith("CMN")) {
            projectid = items[1];
            serviceName = items[0];
            Public = true;
        } else {
            projectid = items[0];
            serviceName = items[1];
        }
        PermissionScope ps = getPermissionScope(projectid);
        if (ps == null) {
            return oldId;
        }
        if (Public) {
            return env + ".CMN." + ps.permission_scope_en_name;
        } else {
            return env + "." + ps.permission_scope_en_name + "." + serviceName;
        }
    }

    private String getNewItem(String item) {
        String[] items = item.split("\\.");
        String projectid;
        String serviceName;
        boolean Public = false;
        if (item.startsWith("CMN")) {
            projectid = items[1];
            serviceName = items[0];
            Public = true;
        } else {
            projectid = items[0];
            serviceName = items[1];
        }
        PermissionScope ps = getPermissionScope(projectid);
        if (ps == null) {
            return item;
        }
        String end = "";
        if (items.length > 2) {
            String[] array = new String[items.length - 2];
            for (int i = 2; i < items.length; i++) {
                array[i - 2] = items[i];
            }
            end = "." + join(array, ".");
        }
        if (Public) {
            return env + ".CMN." + ps.permission_scope_en_name + end;
        } else {
            return env + "." + ps.permission_scope_en_name + "." + serviceName + end;
        }
    }


    private String getNewName(String oldName) {
        String[] item = oldName.split("\\.");
        if (item.length != 3) {
            return oldName;
        }
        return getNewAppId(item[0] + "." + item[1]) + "." + item[2];
    }

    private String getNewMessage(String oldMessage) {
        String[] items = oldMessage.split("\\+");
        String newId = getNewAppId(items[0]);
        return newId + oldMessage.substring(oldMessage.indexOf("+"));
    }


    private String getNewPermissionTarget(String oldTarget) {
        String[] items = oldTarget.split("\\+");
        items[0] = getNewAppId(items[0]);
        if (items.length > 1 && items[1].startsWith("CMN")) {
            items[1] = getNewItem(items[1]);
        }
        return join(items, "+");
    }

    private String getNewRoleName(String oldName) {
        String[] items = oldName.split("\\+");
        if (items.length < 1) {
            return oldName;
        }
        items[1] = getNewAppId(items[1]);
        if (items.length > 2 && items[2].startsWith("CMN")) {
            items[2] = getNewItem(items[2]);
        }
        return join(items, "+");
    }

    private PermissionScope getPermissionScope(String projectid) {
        PermissionScope ps = projectInfo.get(projectid);
        if (ps == null) {
            ps = permissionScopeMapper.select(projectid);
            if (ps != null) {
                projectInfo.put(projectid, ps);
            }
        }
        return ps;
    }

    private String join(String[] coll, String split) {
        if (coll.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (String s : coll) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(split);
            }
            sb.append(s);
        }
        return sb.toString();
    }
}
