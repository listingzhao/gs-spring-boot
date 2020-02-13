package com.leyiju.mapper;

import com.leyiju.domain.Author;

import java.util.List;

public interface AuthorMapper {

    List<Author> getAuthorsByGroupId(Long groupId);
}
