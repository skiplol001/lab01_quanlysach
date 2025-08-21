package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection; // Giả định bạn có lớp này

public class SachDAO {

    public List<Sach> getAll() {
        List<Sach> list = new ArrayList<>();
        String sql = "SELECT * FROM Sach";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Sach sach = new Sach(
                    rs.getInt("MaSach"),
                    rs.getString("TenSach"),
                    rs.getString("TacGia"),
                    rs.getDate("NamXuatBan"),
                    rs.getString("TheLoai"),
                    rs.getInt("SoLuong")
                );
                list.add(sach);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách sách:");
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertSach(Sach sach) {
        String sql = "INSERT INTO Sach (TenSach, TacGia, NamXuatBan, TheLoai, SoLuong) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, sach.getTenSach());
            ps.setString(2, sach.getTacGia());
            ps.setDate(3, sach.getNamXuatBan());
            ps.setString(4, sach.getTheLoai());
            ps.setInt(5, sach.getSoLuong());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        sach.setMaSach(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm sách:");
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSach(Sach sach) {
        String sql = "UPDATE Sach SET TenSach = ?, TacGia = ?, NamXuatBan = ?, TheLoai = ?, SoLuong = ? WHERE MaSach = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sach.getTenSach());
            ps.setString(2, sach.getTacGia());
            ps.setDate(3, sach.getNamXuatBan());
            ps.setString(4, sach.getTheLoai());
            ps.setInt(5, sach.getSoLuong());
            ps.setInt(6, sach.getMaSach());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật sách:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSach(int maSach) {
        String sql = "DELETE FROM Sach WHERE MaSach = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maSach);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa sách:");
            e.printStackTrace();
            return false;
        }
    }

    public List<Sach> searchSach(String keyword) {
        List<Sach> list = new ArrayList<>();
        String sql = "SELECT * FROM Sach WHERE TenSach LIKE ? OR TacGia LIKE ? OR TheLoai LIKE ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            String searchKeyword = "%" + keyword + "%";
            ps.setString(1, searchKeyword);
            ps.setString(2, searchKeyword);
            ps.setString(3, searchKeyword);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Sach sach = new Sach(
                        rs.getInt("MaSach"),
                        rs.getString("TenSach"),
                        rs.getString("TacGia"),
                        rs.getDate("NamXuatBan"),
                        rs.getString("TheLoai"),
                        rs.getInt("SoLuong")
                    );
                    list.add(sach);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm sách:");
            e.printStackTrace();
        }
        return list;
    }
}