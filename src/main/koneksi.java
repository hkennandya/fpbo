/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.sql.*;

/**
 *
 * @author farhan
 */
public class koneksi {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pengingat_tugas", "root", "");
        System.out.println("Berhasil");
        } catch (SQLException e) {
            System.out.println("Gagal");
        }
    }
}
