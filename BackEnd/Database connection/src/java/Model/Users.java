/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
 * @author joonasrl
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId")
    , @NamedQuery(name = "users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username")
    , @NamedQuery(name = "users.findByPasswrd", query = "SELECT u FROM Users u WHERE u.passwrd = :passwrd")
    , @NamedQuery(name = "users.findByDateCreated", query = "SELECT u FROM Users u WHERE u.dateCreated = :dateCreated")
})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    //@Size(max = 40)
    @Column(name = "username", length=40)
    private String username;
    //@Size(max = 40)
    @Column(name = "passwrd", length=40)
    private String passwrd;
    @Column(name = "date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @JoinTable(name = "belongs_to", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "team_id", referencedColumnName = "team_id")})
    @ManyToMany
    private Collection<Team> teamCollection;
    @ManyToMany(mappedBy = "usersCollection")
    private Collection<Files> filesCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Folder> folderCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Files> filesCollection1;

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Users(String username, String passwrd, Date dateCreated) {
        this.username = username;
        this.passwrd = passwrd;
        this.dateCreated = dateCreated;
    }

    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswrd() {
        return passwrd;
    }

    public void setPasswrd(String passwrd) {
        this.passwrd = passwrd;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @XmlTransient
    public Collection<Team> getTeamCollection() {
        return teamCollection;
    }

    public void setTeamCollection(Collection<Team> teamCollection) {
        this.teamCollection = teamCollection;
    }

    @XmlTransient
    public Collection<Files> getFilesCollection() {
        return filesCollection;
    }

    public void setFilesCollection(Collection<Files> filesCollection) {
        this.filesCollection = filesCollection;
    }

    @XmlTransient
    public Collection<Folder> getFolderCollection() {
        return folderCollection;
    }

    public void setFolderCollection(Collection<Folder> folderCollection) {
        this.folderCollection = folderCollection;
    }

    @XmlTransient
    public Collection<Files> getFilesCollection1() {
        return filesCollection1;
    }

    public void setFilesCollection1(Collection<Files> filesCollection1) {
        this.filesCollection1 = filesCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ((Integer)userId).hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        return this.userId == other.userId;
    }

    @Override
    public String toString() {
        return "Model.Users[ userId=" + userId + " ]";
    }
    
}
