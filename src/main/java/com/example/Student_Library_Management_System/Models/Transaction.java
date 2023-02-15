package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;
    private int fine;
    private String transId= UUID.randomUUID().toString();
    @CreationTimestamp
    private Date tranDate;
    private boolean isIssueOperation;

    //connecting to book
    //transaction is child wrt Book
    @ManyToOne
    @JoinColumn
    private Book book;
//need to connect to card class
    @ManyToOne
    @JoinColumn
    private  Card card;

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public boolean isIssueOperation() {
        return isIssueOperation;
    }

    public void setIssueOperation(boolean issueOperation) {
        isIssueOperation = issueOperation;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Transaction(int id, TransactionStatus transactionStatus, int fine, String transId, Date tranDate, boolean isIssueOperation, Book book, Card card) {
        this.id = id;
        this.transactionStatus = transactionStatus;
        this.fine = fine;
        this.transId = transId;
        this.tranDate = tranDate;
        this.isIssueOperation = isIssueOperation;
        this.book = book;
        this.card = card;
    }
}
