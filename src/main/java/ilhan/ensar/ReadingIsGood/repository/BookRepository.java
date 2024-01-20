package ilhan.ensar.ReadingIsGood.repository;

import ilhan.ensar.ReadingIsGood.model.Book;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends BaseRepository<Book> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT e FROM Book e WHERE e.id = :id")
    Optional<Book> findAndLockById(@Param("id") Long id);
}
