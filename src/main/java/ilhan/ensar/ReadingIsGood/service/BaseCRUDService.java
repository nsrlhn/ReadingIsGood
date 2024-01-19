package ilhan.ensar.ReadingIsGood.service;

import ilhan.ensar.ReadingIsGood.model.BaseEntity;

public interface BaseCRUDService<T extends BaseEntity> {

    T getOrThrow(Long id);
}
