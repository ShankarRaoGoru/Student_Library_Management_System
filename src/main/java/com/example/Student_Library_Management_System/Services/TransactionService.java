package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.IssueBookReqDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transaction;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import com.example.Student_Library_Management_System.Repositories.CardRepository;
import com.example.Student_Library_Management_System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CardRepository cardRepository;
    public String IssueBook(IssueBookReqDto issueBookReqDto) throws Exception{
       int bookId= issueBookReqDto.getBookId();
       int cardId=issueBookReqDto.getCardId();
       //getting book and car entity because we need to set transaction entity
       Book book=bookRepository.findById(bookId).get();
       Card card=cardRepository.findById(cardId).get();
       //final goal is to make a transaction entity and set the corresponding attributes
        Transaction transaction=new Transaction();
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setIssueOperation(true);
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setTransId(UUID.randomUUID().toString());
        //remaining attributes r success and failure
        //check for validations
        if(book==null || book.isIssued()==true){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not available");
        }
        if(card==null || card.getCardStatus()!= CardStatus.ACTIVATED){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Card is not valid");
        }
        //reached success case now
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        //setting attributes of book
       book.setIssued(true);
       //between book and transcation
        List<Transaction> listoftransactionforbook=book.getTransactionList();
        listoftransactionforbook.add(transaction);
        book.setTransactionList(listoftransactionforbook);
        //similarly for book and card
        List<Book>booksIsuuedforCard=card.getBooksIssued();
        booksIsuuedforCard.add(book);
        card.setBooksIssued(booksIsuuedforCard);
        for(Book book1:booksIsuuedforCard){
            System.out.println(book1.getName());
        }
        //similarly for card and transaction
        List<Transaction>translistforCard=card.getListoftransaction();
        translistforCard.add(transaction);
        card.setListoftransaction(translistforCard);
        //saving the parent
        cardRepository.save(card);
        //by cascading effect both book and transaction will be saved
        return "Book issued successfully";
    }
    public String getTransEntry(int bookId,int cardId){
        List<Transaction>list=transactionRepository.getTrasnEntry(bookId,cardId);
        String tranId=list.get(0).getTransId();
        return tranId;

    }
}
