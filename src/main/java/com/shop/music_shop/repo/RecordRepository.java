package com.shop.music_shop.repo;

import com.shop.music_shop.models.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends PagingAndSortingRepository<Record, Long> {
  Page<Record> findByNameContainingIgnoreCase(String name, Pageable pageable);
  Page<Record> findByMusicianContainingIgnoreCase(String name, Pageable pageable);
}
