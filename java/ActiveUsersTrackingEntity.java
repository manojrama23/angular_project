package com.smart.rct.common.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "active_users_tracking")
public class ActiveUsersTrackingEntity {

private Integer activeUsers;
private Integer activeSessions;
private Timestamp timestamp;


@Column(name = "active_users")
public Integer getActiveUsers() {
return activeUsers;
}

public void setActiveUsers(Integer activeUsers) {
this.activeUsers = activeUsers;
}

@Column(name = "active_sessions")
public Integer getActiveSessions() {
return activeSessions;
}

public void setActiveSessions(Integer activeSessions) {
this.activeSessions = activeSessions;
}

@Id
@Column(name = "timestamp")
public Timestamp getTimestamp() {
return timestamp;
}

public void setTimestamp(Timestamp timestamp) {
this.timestamp = timestamp;
}


/*@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id
@Column(name = "id")
public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}*/


}