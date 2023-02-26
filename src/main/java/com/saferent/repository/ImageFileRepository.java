package com.saferent.repository;

import com.saferent.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface ImageFileRepository extends JpaRepository<ImageFile,String> {
}
