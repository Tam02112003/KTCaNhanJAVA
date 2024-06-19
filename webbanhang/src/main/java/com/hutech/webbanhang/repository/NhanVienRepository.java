package com.hutech.webbanhang.repository;

import com.hutech.webbanhang.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
// Các phương thức tùy biến…
}
