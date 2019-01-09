package com.giedrius.assignment.app.model;

import com.giedrius.assignment.app.business.AddLoan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String[] address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private List<ClientInfo> clientInfo;

    @Transient
    private AddLoan addLoan;

    public AddLoan getAddLoan() {
        return addLoan = new AddLoan();
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAddress() {
        return address;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    public List<ClientInfo> getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo info) {
        if (clientInfo == null) {
            this.clientInfo = new ArrayList<>();
            if (info.getDateTime() == null) {
                info.setDateTime(LocalDateTime.now());
            }
            if (info.getIpAddress() == null) {
                InetAddress localhost = null;
                try {
                    localhost = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                info.setIpAddress("" + localhost.getHostAddress().trim());
            }
            if (info.getLoan() == 0L) {
                info.setLoan(0L);
            }
            if (info.getTerm() == 0L) {
                info.setTerm(0L);
            }
            if (info.getExpirationDate() == null) {
                String str = "1986-04-08 12:30";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
                info.setExpirationDate(dateTime);
            }
        }
        this.clientInfo.add(info);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", address=" + Arrays.toString(address) +
                ", clientInfo=" + clientInfo +
                '}';
    }
}




