/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import daos.FileDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import dtos.RegistrationDTO;
import implement.RegistrationInterface;

/**
 *
 * @author Le Phuoc Duy
 */
public class RegistrationServer extends UnicastRemoteObject implements RegistrationInterface {

    private final String fileName = "RegistrationData.txt";
    private ArrayList<RegistrationDTO> list;
    private FileDAO dao;

    public RegistrationServer() throws RemoteException {
        super();
        dao = new FileDAO(fileName);
        list = findAllRegistrations() == null ? new ArrayList<>() : findAllRegistrations();

    }

    @Override
    public boolean createRegistration(RegistrationDTO dto) throws RemoteException {
        list.add(dto);
        return dao.putContent(list);
    }

    @Override
    public RegistrationDTO findByRegistrationID(String id) throws RemoteException {
        ArrayList<RegistrationDTO> listFind = findAllRegistrations();
        for (RegistrationDTO obj : listFind) {
            if (obj.getRegistrationID().equals(id)) {
                return obj;
            }
        }
        return null;
    }

    @Override
    public ArrayList<RegistrationDTO> findAllRegistrations() throws RemoteException {
        return (ArrayList<RegistrationDTO>) dao.getContent();
    }

    @Override
    public boolean removeRegistration(String id) throws RemoteException {
        for(RegistrationDTO obj : list){
            if(obj.getRegistrationID().equals(id)){
                System.out.println(list.remove(obj));
                dao.putContent(list);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateRegistration(RegistrationDTO dto) throws RemoteException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRegistrationID().equals(dto.getRegistrationID())) {
                list.set(i, dto);
                return dao.putContent(list);
            }
        }
        return false;
    }
}
