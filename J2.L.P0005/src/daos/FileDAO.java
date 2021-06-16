/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.RegistrationDTO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author Le Phuoc Duy
 */
public class FileDAO {

    private FileReader fr;
    private BufferedReader br;
    private FileWriter fw;
    private BufferedWriter bw;
    private static final String DELIMETER = ";";

    private void closeFileStream() {
        try {
            if (br != null) {
                br.close();
            }
            if (bw != null) {
                bw.close();
            }
            if (fr != null) {
                fr.close();
            }
            if (fw != null) {
                fw.close();
            }
        } catch (Exception e) {
            System.err.println("Error while close file stream at: " + FileDAO.class.getName());
        }
    }

    private String fileName;

    public FileDAO(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<RegistrationDTO> getContent() {
        ArrayList<RegistrationDTO> list = null;
        RegistrationDTO dto = null;
        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String line;
            list = new ArrayList();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(DELIMETER);
                String regID = data[0];
                String fullName = data[1];
                String email = data[2];
                String phone = data[3];
                String address = data[4];
                int age = Integer.parseInt(data[5]);
                int numOfMem = Integer.parseInt(data[6]);
                int numOfChild = Integer.parseInt(data[7]);
                int numOfAdults = Integer.parseInt(data[8]);
                boolean gender = data[9].equals("male") ? true : (data[9].equals("female") ? false : false);
                if (dto == null) {
                    dto = new RegistrationDTO();
                }
                if (data.length == 10) {
                    dto = new RegistrationDTO(regID, fullName, email, phone, address, age, numOfMem, numOfChild, numOfAdults, gender);

                    list.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeFileStream();
        }
        return list;
    }

    public boolean putContent(ArrayList<RegistrationDTO> list) {
        try {
            fw = new FileWriter(fileName, false);
            bw = new BufferedWriter(fw);
            for (RegistrationDTO dto : list) {
                String data = new StringBuilder(dto.getRegistrationID())
                        .append(";").append(dto.getFullName()).append(";")
                        .append(dto.getEmail()).append(";").append(dto.getPhone())
                        .append(";").append(dto.getAddress()).append(";")
                        .append(dto.getAge()).append(";").append(dto.getNumberOfMember())
                        .append(";").append(dto.getNumberOfChildren()).append(";")
                        .append(dto.getNumberOfAdults()).append(";")
                        .append(dto.isGender() ? "male" : "female").toString();
                bw.write(data);
                bw.newLine();
                bw.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeFileStream();
        }
        return true;
    }

}
