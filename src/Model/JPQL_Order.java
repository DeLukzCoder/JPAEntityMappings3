/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lukasz Krawczyk
 */
@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JPQL_Order.findAll", query = "SELECT j FROM JPQL_Order j"),
    @NamedQuery(name = "JPQL_Order.findByOrderNumber", query = "SELECT j FROM JPQL_Order j WHERE j.orderNumber = :orderNumber"),
    @NamedQuery(name = "JPQL_Order.findByOrderDate", query = "SELECT j FROM JPQL_Order j WHERE j.orderDate = :orderDate"),
    @NamedQuery(name = "JPQL_Order.findByRequiredDate", query = "SELECT j FROM JPQL_Order j WHERE j.requiredDate = :requiredDate"),
    @NamedQuery(name = "JPQL_Order.findByShippedDate", query = "SELECT j FROM JPQL_Order j WHERE j.shippedDate = :shippedDate"),
    @NamedQuery(name = "JPQL_Order.findByStatus", query = "SELECT j FROM JPQL_Order j WHERE j.status = :status"),
    @NamedQuery(name = "JPQL_Order.findByStatusAndCustomer", query = "SELECT j FROM JPQL_Order j WHERE j.status = :status AND j.customerNumber = :customerNumber")})
public class JPQL_Order implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "orderNumber")
    private Integer orderNumber;
    @Basic(optional = false)
    @Column(name = "orderDate")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Basic(optional = false)
    @Column(name = "requiredDate")
    @Temporal(TemporalType.DATE)
    private Date requiredDate;
    @Column(name = "shippedDate")
    @Temporal(TemporalType.DATE)
    private Date shippedDate;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Lob
    @Column(name = "comments")
    private String comments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jPQLOrder")
    private List<Orderdetail> orderdetailList;
    @JoinColumn(name = "customerNumber", referencedColumnName = "customerNumber")
    @ManyToOne(optional = false)
    private Customer customerNumber;

    public JPQL_Order() {
    }

    public JPQL_Order(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public JPQL_Order(Integer orderNumber, Date orderDate, Date requiredDate, String status) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.status = status;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @XmlTransient
    public List<Orderdetail> getOrderdetailList() {
        return orderdetailList;
    }

    public void setOrderdetailList(List<Orderdetail> orderdetailList) {
        this.orderdetailList = orderdetailList;
    }

    public Customer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Customer customerNumber) {
        this.customerNumber = customerNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderNumber != null ? orderNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JPQL_Order)) {
            return false;
        }
        JPQL_Order other = (JPQL_Order) object;
        if ((this.orderNumber == null && other.orderNumber != null) || (this.orderNumber != null && !this.orderNumber.equals(other.orderNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.JPQL_Order[ orderNumber=" + orderNumber + " ]";
    }
    
}
