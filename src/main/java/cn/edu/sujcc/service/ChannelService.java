package cn.edu.sujcc.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.sujcc.dao.ChannelRepository;
import cn.edu.sujcc.model.Channel;

/**�ṩƵ�����ҵ���߼���
 * @author dell
 * 
 */
@Service
public class ChannelService {
	@Autowired
	private ChannelRepository repo;
	
	/**
	 * ��ȡһ��Ƶ��
	 * @param id
	 * @return
	 */
	public Channel getChannel(String channelId) {
		Optional<Channel> result = repo.findById(channelId);
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}
	
	/**
	 * ��ȡ����Ƶ��
	 * @return Ƶ��List
	 */
	public List<Channel> getAllChannels(){
		return repo.findAll();
	}
	
	/**
	 * ɾ��ָ��Ƶ��
	 * @param id
	 * @return
	 */
	public boolean deleteChannel(String channelId) {
		boolean result = true;
		repo.deleteById(channelId);
		return result;
	}
	
	/**
	 * ����һ��Ƶ��
	 * @param c �����µ�Ƶ��
	 * @return ���º��Ƶ��
	 */
	public Channel updateChannel(Channel c) {
		Channel saved = getChannel(c.getId());
		if(c.getTitle() != null) {
			saved.setTitle(c.getTitle());
		}
		if(c.getQuality() != null) {
			saved.setQuality(c.getQuality());
		}
		if(c.getUrl() != null) {
			saved.setUrl(c.getUrl());
		}
		if(c.getComments() != null) {
			saved.getComments().addAll(c.getComments());
		}else {
			saved.setComments(c.getComments());
		}
		return repo.save(saved);
	}
	
	
	public List<Channel> searchByQuality(String quality){
		return repo.findByQuality(quality);
	}
	public List<Channel> searchByTitle(String title){
		return repo.findByTitle(title);
	}
	/**
	 * 
	 * 
	 */
	public List<Channel> getLatestCommentsChannel(){
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime today = LocalDateTime.of(now.getYear(),
				now.getMonthValue(), now.getDayOfMonth(),0,0);
		return repo.findByCommentsDtAfter(today);
	}

	/**
	 * �½�Ƶ��
	 * @param c
	 * @return
	 */
	public Channel createChannel(Channel c) {
		return repo.save(c);
	}

}
