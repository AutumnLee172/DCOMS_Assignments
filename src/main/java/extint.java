import java.rmi.*;
import java.net.*;
import java.lang.Math;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author Autumn
 */
public class extint extends UnicastRemoteObject implements RMIinterface{
    public extint()throws RemoteException
    {   
        super();
    }
    @Override
    public int add(int x, int y)throws RemoteException
    {
        return x+y;
    }
    @Override
    public double circlearea(double r)throws RemoteException
    {
        return 3.142*(Math.pow(r, 2));
    }
    @Override
    public double circumference(double r)throws RemoteException
    {
        return 2*3.142*r;
    }
    @Override
    public double diameter(double r)throws RemoteException
    {
        return 2*r;
    }
}
