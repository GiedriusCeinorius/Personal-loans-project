package com.giedrius.assignment.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Entity
@Table(name = "clientInfo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "dateTime")
    private LocalDateTime dateTime;

    @Column(name = "ipAddress")
    private String ipAddress;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @Column(name = "loan")
    private Long loan = 0L;

    @Column(name = "expirationDate")
    private LocalDateTime expirationDate;

    @Column(name = "term")
    private Long term = 0L;

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Long getTerm() {
        return term;
    }

    public void setTerm(Long term) {
        this.term = term;
    }

    public int getId() {
        return id;
    }

    public Long getLoan() {
        return loan;
    }

    public void setLoan(Long loan) {
        this.loan = loan;
    }


    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime time) {
        if (time == null) {
            this.dateTime = LocalDateTime.now();
        } else {
            this.dateTime = time;
        }
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        if (ipAddress == null) {
            InetAddress localhost = null;
            try {
                localhost = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            this.ipAddress = "" + localhost.getHostAddress().trim();
        } else {
            this.ipAddress = ipAddress;
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "ClientInfo{" +
                "dateTime=" + dateTime +
                ", ipAddress='" + ipAddress + '\'' +
                ", loan=" + loan +
                ", expirationDate=" + expirationDate +
                ", term=" + term +
                '}';
    }
}
