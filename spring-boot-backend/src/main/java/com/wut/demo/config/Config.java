package com.wut.demo.config;

import com.wut.demo.entity.Book;
import com.wut.demo.entity.Comment;
import com.wut.demo.repository.BookRepository;
import com.wut.demo.repository.CommentRepository;
import com.wut.demo.repository.UserRepository;
import com.wut.demo.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class Config {

    // Add test Users
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, BookRepository bookRepository, CommentRepository commentRepository) {
        return args -> {
            User user1 = new User("Güven", "Tuncay");
            User user2 = new User("İkbal", "Kılıç");
            User user3 = new User("Ebru", "Yaşar");
            User user4 = new User("Aslı", "Türkdönmez");

            userRepository.saveAll(List.of(user1, user2, user3, user4));

            Book book1 = new Book("Dünyanın Sonundaki Saat",
                    "Yunus Meşe",
                    176,
                    LocalDate.of(2020, 3, 4));
            Book book2 = new Book("İrade Terbiyesi",
                    "Jules Payot",
                    230,
                    LocalDate.of(2020, 5, 14));

            bookRepository.saveAll(List.of(book1, book2));

            Comment comment1 = new Comment("Harika! Yazar yine yapmış yapacağını.", user1, book1);

            Comment comment2 = new Comment("Bu nasıl bir kitap. Hemen iade ediyorum.", user2, book2);

            Comment comment3 = new Comment("Mükemmel bir kitap. Bayıldım!", user3, book1);

            Comment comment4 = new Comment("Çok sıkıcı. Birdaha da bu yazardan bir kitap bile almam..", user4, book2);

            commentRepository.saveAll(List.of(comment1, comment2, comment3, comment4));

        };
    }


}
