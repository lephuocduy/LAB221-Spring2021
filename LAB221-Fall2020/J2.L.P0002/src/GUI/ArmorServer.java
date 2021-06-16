package GUI;

import DAO.ArmorFileDAO;
import DTO.ArmorDTO;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author truon
 */
public class ArmorServer extends UnicastRemoteObject implements ArmorInterface {

    public ArmorServer() throws RemoteException {
    }

    @Override
    public boolean createArmor(ArmorDTO dto) {
        List<ArmorDTO> listArmor = ArmorFileDAO.readFile("ArmorData.txt");
        listArmor.add(dto);
        ArmorFileDAO.writeFile("ArmorData.txt", listArmor);
        return true;
    }

    @Override
    public ArmorDTO findByArmorID(String id) {
        ArmorDTO armor = null;
        List<ArmorDTO> listArmor = ArmorFileDAO.readFile("ArmorData.txt");
        for (ArmorDTO armorDTO : listArmor) {
            if (armorDTO.getArmorId().equals(id)) {
                armor = new ArmorDTO(armorDTO.getArmorId(), armorDTO.getClassification(),
                        armorDTO.getDescription(), armorDTO.getStatus(), armorDTO.getTimeOfCreate(), armorDTO.getDefense());
            }
        }
        return armor;
    }

    @Override
    public List<ArmorDTO> findAllArmor() {
        List<ArmorDTO> listArmor = new ArrayList<>();
        listArmor = ArmorFileDAO.readFile("ArmorData.txt");
        return listArmor;
    }

    @Override
    public boolean removeArmor(String id) {
        List<ArmorDTO> listArmor = ArmorFileDAO.readFile("ArmorData.txt");  
        for (ArmorDTO armorDTO : listArmor) {
            if (armorDTO.getArmorId().equals(id)) {           
                listArmor.remove(armorDTO);
                    ArmorFileDAO.writeFile("ArmorData.txt", listArmor);
                    return true;
            }
        }         
        return false;
    }

    @Override
    public boolean updateArmor(ArmorDTO dto) {
        List<ArmorDTO> listArmor = ArmorFileDAO.readFile("ArmorData.txt");
        for (ArmorDTO armorDTO : listArmor) {
            if (armorDTO.getArmorId().equals(dto.getArmorId())) {
                armorDTO.setClassification(dto.getClassification());
                armorDTO.setDescription(dto.getDescription());
                armorDTO.setStatus(dto.getStatus());
                armorDTO.setTimeOfCreate(dto.getTimeOfCreate());
                armorDTO.setDefense(dto.getDefense());
            }
        }
        ArmorFileDAO.writeFile("ArmorData.txt", listArmor);
        return true;
    }

    public static void main(String[] args) throws RemoteException {
        try {
            ArmorServer ars = new ArmorServer();
            LocateRegistry.createRegistry(8888);
            Runtime r = Runtime.getRuntime();
            r.exec("rmiregistry.exe");
            Naming.rebind("rmi://127.0.0.1:8888/now", ars);
            JOptionPane.showMessageDialog(null, "server is ready");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "has run server");
            System.exit(0);
        }
    }

}
