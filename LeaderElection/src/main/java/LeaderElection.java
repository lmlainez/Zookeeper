import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.server.quorum.Leader;

import java.io.IOException;

public class LeaderElection implements Watcher {

    private static final String ZOOKEPER_ADDRESS = "localhost:2181";
    private ZooKeeper zookeeper;
    private static final int SESSION_TIMEOUT=3000;
    public static void main(String[] args) throws IOException, InterruptedException {
        LeaderElection leaderElection = new LeaderElection();
        leaderElection.connectToZookeeper();
        leaderElection.run();
        leaderElection.close();
        System.out.println("Disconnected from Zookeeper, exiting application");
    }

    public void connectToZookeeper() throws IOException {
        this.zookeeper =  new ZooKeeper(ZOOKEPER_ADDRESS, SESSION_TIMEOUT, this);
    }

    public void run() throws InterruptedException {
        synchronized (zookeeper){
            zookeeper.wait();
        }
    }

    public void close() throws InterruptedException {
        this.zookeeper.close();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        switch (watchedEvent.getType()){
            case None:
                if(watchedEvent.getState() == Event.KeeperState.SyncConnected ){
                    System.out.println("Successfully Connected to Zookeper ");
                }else{
                    synchronized (zookeeper){
                        System.out.println("Disconnected from zookeper ");
                        zookeeper.notifyAll();
                    }
                }
        }
    }
}
