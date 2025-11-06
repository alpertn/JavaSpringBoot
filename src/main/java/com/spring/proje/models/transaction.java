package com.spring.proje.models;

import java.time.LocalDateTime;

public class transaction {
    private Long id;
    private String gondereniban;
    private String gonderileniban;
    private double islemtutari;
    private LocalDateTime transfervtarihi;

    public transaction() {}

    public transaction(Long id, String gondereniban, String gonderileniban, double islemtutari) {
        this.id = id;
        this.gondereniban = gondereniban;
        this.gonderileniban = gonderileniban;
        this.islemtutari = islemtutari;
        this.transfervtarihi = LocalDateTime.now();
    }

    public Long cek_id() {
        return id;
    }

    public void belirle_id(Long id) {
        this.id = id;
    }

    public String cek_gondereniban() {
        return gondereniban;
    }

    public void belirle_gondereniban(String geleniban) {
        this.gondereniban = geleniban;
    }

    public String cek_gonderilen() {
        return gonderileniban;
    }

    public void belirle_gonderileniban(String gonderileniban) {
        this.gonderileniban = gonderileniban;
    }

    public double cek_islemtutari() {
        return islemtutari;
    }

    public void belirle_islemtutari(double bakiye) {
        this.islemtutari = bakiye;
    }

    public LocalDateTime cek_transvertarihi() {
        return transfervtarihi;
    }

    public void belirle_transfertarihi(LocalDateTime transfervtarihi) {
        this.transfervtarihi = transfervtarihi;
    }
}
