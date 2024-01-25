package ilhan.ensar.ReadingIsGood.model.listener;

import ilhan.ensar.ReadingIsGood.model.BaseEntity;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Log4j2
public class EntityListener {

    @PostPersist
    protected void logPost(BaseEntity entity) {
        String username = getUsername();

        log.info(username + " - Created: " + entity.toString());
    }

    @PostUpdate
    protected void logUpdate(BaseEntity entity) {
        String username = getUsername();

        log.info(username + " - Updated:" + entity.toString());
    }

    private String getUsername() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return ((UserDetails) principal).getUsername();
        } catch (Exception e) {
            return "System";
        }
    }
}
