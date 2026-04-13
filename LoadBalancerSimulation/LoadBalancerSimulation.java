import java.util.*;

class Request {
    private int id;

    public Request(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

class Server {
    private String name;
    private int load;

    public Server(String name) {
        this.name = name;
        this.load = 0;
    }

    public void handleRequest(Request req) {
        System.out.println(name + " handling request " + req.getId());
    }

    public void incrementLoad() {
        load++;
    }

    public void decrementLoad() {
        load--;
    }

    public int getLoad() {
        return load;
    }

    public String getName() {
        return name;
    }
}

interface LoadBalancer {
    Server getServer();
}

abstract class LoadBalancerBase implements LoadBalancer {
    protected List<Server> servers;
    protected Map<Server, Integer> loadMap;

    public LoadBalancerBase(List<Server> servers) {
        this.servers = servers;
        this.loadMap = new HashMap<>();

        for (Server s : servers) {
            loadMap.put(s, 0);
        }
    }

    public void updateLoad(Server s, int change) {
        loadMap.put(s, loadMap.get(s) + change);
    }

    public int getLoad(Server s) {
        return loadMap.get(s);
    }
}

class RoundRobinLB extends LoadBalancerBase {
    private int index = 0;

    public RoundRobinLB(List<Server> servers) {
        super(servers);
    }

    public Server getServer() {
        Server s = servers.get(index);
        index = (index + 1) % servers.size();
        return s;
    }
}

class LeastLoadLB extends LoadBalancerBase {
    public LeastLoadLB(List<Server> servers) {
        super(servers);
    }

    public Server getServer() {
        Server minServer = null;
        int minLoad = Integer.MAX_VALUE;

        for (Server s : servers) {
            int load = getLoad(s);
            if (load < minLoad) {
                minLoad = load;
                minServer = s;
            }
        }
        return minServer;
    }
}

public class LoadBalancerSimulation {
    public static void main(String[] args) {
        List<Server> servers = new ArrayList<>();
        servers.add(new Server("Server-1"));
        servers.add(new Server("Server-2"));
        servers.add(new Server("Server-3"));

        LoadBalancerBase lb = new RoundRobinLB(servers);

        for (int i = 1; i <= 10; i++) {
            Request req = new Request(i);
            Server s = lb.getServer();
            lb.updateLoad(s, 1);
            s.handleRequest(req);
            lb.updateLoad(s, -1);
        }
    }
}