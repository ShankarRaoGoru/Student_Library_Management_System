package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.IssueBookReqDto;
import com.example.Student_Library_Management_System.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @PostMapping("/isuue-book")
    public String IssueBook(@RequestBody IssueBookReqDto issueBookReqDto){
        try {
            return transactionService.IssueBook(issueBookReqDto);
        }
        catch(Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("/getTransinfo")
    public String getTransEntry(@RequestParam("bookId")int bookId,@RequestParam("cardId")int cardId){
        return transactionService.getTransEntry(bookId,cardId);
    }
}
