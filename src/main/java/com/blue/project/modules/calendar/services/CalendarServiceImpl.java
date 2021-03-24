package com.blue.project.modules.calendar.services;

import com.blue.project.modules.calendar.annontation.CalendarServiceProvider;
import com.blue.project.modules.calendar.dto.EventContext;
import com.blue.project.modules.calendar.dto.EventData;
import name.mymiller.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private ApplicationContext applicationContext;

    private List<CalendarServiceProviderInterface> findCalendarServiceProviders() {
        ClassPathScanningCandidateComponentProvider provider
                = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(CalendarServiceProvider.class));

        Set<BeanDefinition> providers = provider.findCandidateComponents("com.blue.project");

        return providers.stream().map(BeanDefinition::getBeanClassName).map(name -> {
            return name.substring(name.lastIndexOf('.')+1);
        }).map(beanName -> {
            return Character.toLowerCase(beanName.charAt(0)) + beanName.substring(1);
        }).map(beanName -> {
            return applicationContext.getBean(beanName);
        }).map(bean -> {
            if(bean instanceof CalendarServiceProviderInterface) {
                return (CalendarServiceProviderInterface)bean;
            }
            return null;
        }).collect(Collectors.toList());
    }

    @Override
    public List<EventData> getEventData(ZonedDateTime start, ZonedDateTime end) {
        List<CalendarServiceProviderInterface> calendarServiceProviders = ListUtils.safe(this.findCalendarServiceProviders());
        List<EventData> eventDataList = new ArrayList<>();

        calendarServiceProviders.stream().forEach(provider -> {
            eventDataList.addAll(provider.getEventData(start,end));
        });

        eventDataList.forEach(eventData -> {
            eventData.setColor(this.getEventContextColor(eventData.getOrganization(),eventData.getType()));
        });

        return eventDataList;
    }

    private String getEventContextColor(String organization, String type) {
        return "Red";
    }

    @Override
    public List<EventContext> getEventContext() {
        List<CalendarServiceProviderInterface> calendarServiceProviders = ListUtils.safe(this.findCalendarServiceProviders());
        List<EventContext> eventContextList = new ArrayList<>();

        calendarServiceProviders.forEach(provider -> {
            eventContextList.addAll(provider.getEventContext());
        });
        return eventContextList;
    }
}
