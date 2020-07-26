package com.manage.cov.covman.services;

import com.manage.cov.covman.entity.Modification;
import com.manage.cov.covman.entity.Parent;
import com.manage.cov.covman.repositories.ParentRepository;
import com.manage.cov.covman.utils.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ParentService {

    private ParentRepository parentRepository;

    @Autowired
    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    /**
     * Add a parent or return null in case of error
     * @param parent
     * @return Parent
     */
    public Parent addParent(Parent parent){
        Parent created = null;

        try {
            parent.setModification(new Modification(LocalDateTime.now(), parent.getUser().getEmail()));
            parent.getUser().setRole(RoleEnum.PARENT);
            created = parentRepository.save(parent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return created;
    }

    public Parent getParent(Long id) {
        Optional<Parent> parent = parentRepository.findById(id);

        if (parent.isPresent()) {
            return parent.get();
        }

        return null;
    }

    public Parent getParentByEmail(Long userId) {
        return parentRepository.findByUserId(userId);
    }
}
