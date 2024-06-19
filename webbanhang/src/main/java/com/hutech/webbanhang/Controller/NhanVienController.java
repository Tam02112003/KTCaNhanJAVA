package com.hutech.webbanhang.Controller;

import com.hutech.webbanhang.model.NhanVien;
import com.hutech.webbanhang.service.PhongBanService;
import com.hutech.webbanhang.service.NhanVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhanvien")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private PhongBanService phongBanService;

    @GetMapping
    public String showNhanVienList(Model model) {
        model.addAttribute("nhanvien", nhanVienService.getAllNhanvien());
        return "/nhanvien/nhanvien-list";
    }

    // For adding a new product
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("nhanvien", new NhanVien());
        model.addAttribute("phongban", phongBanService.getAllPhongBan());
        return "/nhanvien/add-nhanvien";
    }

    @PostMapping("/add")
    public String addProduct(@Valid NhanVien nhanvien, BindingResult result) {
        if (result.hasErrors()) {
            return "/nhanvien/add-nhanvien";
        }


        nhanVienService.addNhanVien(nhanvien);
        return "redirect:/nhanvien";
    }



    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        NhanVien nhanvien = nhanVienService.getNhanVienById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid nhan vien Id:" + id));
        model.addAttribute("nhanvien", nhanvien);
        model.addAttribute("phongban", phongBanService.getAllPhongBan());
        return "/nhanvien/update-nhanvien";
    }

    @PostMapping("/update/{id}")
    public String updateNhanVien(@PathVariable Long id, @Valid NhanVien nhanvien, BindingResult result) {
        if (result.hasErrors()) {
            nhanvien.setId(id);
            return "/nhanvien/update-nhanvien";
        }
        nhanVienService.updateNhanVien(nhanvien);
        return "redirect:/nhanvien";
    }

    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        nhanVienService.deleteNhanVienById(id);
        return "redirect:/nhanvien";
    }
}