package persistence;

import org.json.JSONObject;

public interface Writable {
    /* EFFECTS: returns this as a JSON object
       REQUIRES: object is originally not a JSON object
       MODIFIES: this */
    JSONObject toJson();
}
