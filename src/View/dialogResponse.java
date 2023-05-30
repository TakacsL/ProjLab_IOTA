package View;

/**
 * Class for holding special dialog responses for selecting a game object by ID
 */
public class dialogResponse {
    /**
     * true if the dialog is closed with OK
     * false if the dialog is cancelled
     */
    public boolean OK;
    /**
     * Player ID which will perform the action
     */
    public int playerID;
    /**
     * Area ID on which the action will be performed
     */
    public int areaID;

    /**
     * Construct an instance
     * @param OK dialog result
     * @param playerID playerID who performs the action
     * @param areaID areaID on which the action will be performed
     */
    public dialogResponse(boolean OK, int playerID, int areaID) {
        this.OK = OK;
        this.playerID = playerID;
        this.areaID = areaID;
    }
}
