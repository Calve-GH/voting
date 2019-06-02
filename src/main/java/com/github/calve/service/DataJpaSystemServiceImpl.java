package com.github.calve.service;

import com.github.calve.repository.JpaUtil;
import com.github.calve.repository.datajpa.CrudVoteLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("systemService")
public class DataJpaSystemServiceImpl implements SystemService {

    private CrudVoteLogRepository voteLogRepo;
    private JpaUtil jpaUtil;

    @Autowired
    public DataJpaSystemServiceImpl(CrudVoteLogRepository voteLogRepo,
                                    JpaUtil jpaUtil) {
        this.voteLogRepo = voteLogRepo;
        this.jpaUtil = jpaUtil;
    }

    @Transactional
    @Override
    public void resetAndLogVoteSystem() {
/*        List<Menu> dailyVoteList = menuRepo.findAllByDate(LocalDate.now());
        historyRepo.saveAll(MenuUtil.convertMenuListToHistoryList(dailyVoteList));
        menuRepo.deleteAll(dailyVoteList);
        voteLogRepo.deleteAll();*/
        clearCache();
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
/*        List<Menu> unsavedMenus = menuRepo.findByDateBefore(LocalDate.now());
        historyRepo.saveAll(MenuUtil.convertMenuListToHistoryList(unsavedMenus));
        menuRepo.deleteAll(unsavedMenus);
        voteLogRepo.deleteAllByDateBefore(LocalDate.now());*/
    }

    private void clearCache() {
        if (jpaUtil != null) {
            jpaUtil.clear2ndLevelHibernateCache();
        }
    }
}
