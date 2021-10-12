package com.jp.library.dao;

import com.jp.library.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag,Integer> {

    public Optional<List<Tag>> findTagByName(String name);
}
