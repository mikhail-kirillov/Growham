package gd.rf.theoneboringmancompany.growham.tools;

public class Database implements AndroidConnector {
    private AndroidConnector connector;

    public Database(AndroidConnector connector){
        this.connector = connector;
    }

    @Override
    public void insert(String Name, int Time) {
        connector.insert(Name, Time);
    }

    @Override
    public Object[] selectToScore() {
        return connector.selectToScore();
    }
}
