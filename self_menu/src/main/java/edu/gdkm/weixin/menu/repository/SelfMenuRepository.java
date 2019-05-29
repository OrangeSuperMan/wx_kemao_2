package edu.gdkm.weixin.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.gdkm.weixin.menu.domain.SelfMenu;

@Repository
public interface SelfMenuRepository extends JpaRepository<SelfMenu, String> {

}
