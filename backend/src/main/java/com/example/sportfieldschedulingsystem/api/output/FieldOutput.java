package com.example.sportfieldschedulingsystem.api.output;

import com.example.sportfieldschedulingsystem.dto.FieldDTO;

import java.util.ArrayList;
import java.util.List;

public class FieldOutput {
    private int page;
    private int size;
    private int totalPage;
    private List<FieldDTO> list = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<FieldDTO> getList() {
        return list;
    }

    public void setList(List<FieldDTO> list) {
        this.list = list;
    }

    public void calTotalPage(int size, int totalItem) {
        this.totalPage = (int) Math.ceil((double) totalItem / size);
    }
}
