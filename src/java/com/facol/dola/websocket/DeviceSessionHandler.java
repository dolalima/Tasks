/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facol.dola.websocket;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.Session;
import com.facol.dola.models.Activity;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonObject;

@ApplicationScoped
public class DeviceSessionHandler {
    private final Set sessions = new HashSet<>();
    private final Set activities = new HashSet<>();
    
    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }
    
    public List getDevices() {
        return new ArrayList<>(activities);
    }

    public void addDevice(Activity activity) {
    }

    public void removeDevice(int id) {
    }

    public void toggleDevice(int id) {
    }

    private Activity getDeviceById(int id) {
        return null;
    }

    private JsonObject createAddMessage(Activity device) {
        return null;
    }

    private void sendToAllConnectedSessions(JsonObject message) {
    }

    private void sendToSession(Session session, JsonObject message) {
    }
}
