package org.example.springbootweb.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.springbootweb.Exception.DataProcessingException;
import org.example.springbootweb.model.Book;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Book createBook(Book book) {
        EntityTransaction transaction = null;
        try(EntityManager manager = entityManagerFactory.createEntityManager()) {
            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Failed to create book object");
        }
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Book book = entityManager.find(Book.class, id);
            return Optional.ofNullable(book);
        } catch (Exception e) {
            throw new DataProcessingException("Failed to get book id " + id );
        }
    }

    @Override
    public List<Book> getAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("select b from Book b", Book.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Filed to get all books");
        }
    }
}
