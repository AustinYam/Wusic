package com.sjsu.wusic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjsu.wusic.dao.AlbumRepository;
import com.sjsu.wusic.dao.ArtistRepository;
import com.sjsu.wusic.dao.SongRepository;
import com.sjsu.wusic.model.Album;
import com.sjsu.wusic.model.Artist;
import com.sjsu.wusic.model.Song;

@Controller
public class AlbumController {
	
	@Autowired
	private AlbumRepository albumDao;
	@Autowired
	private AlbumRepository albumsByArtistDao;
	@Autowired
	private ArtistRepository artistDao;
	@Autowired 
	private SongRepository albumSongsDao; 
	
	@RequestMapping("/get_album_songs")
	 public String songsInAlbum(Model model, @RequestParam(value = "name") String name) {
       List<Song> songsInAlbum = albumSongsDao.findByAlbumName(name);
       Album album = albumDao.findByName(name);
       model.addAttribute("album_name", album.getName());
       model.addAttribute("songs", songsInAlbum);
       
       return "displaySongsInAlbum";

   }
	
	@RequestMapping("/get_artist_albums")
	 public String albumsByArtist(Model model, @RequestParam(value = "artist_id") String id) {
		System.out.println("sd");
        List<Album> albumsByArtist = albumsByArtistDao.findByArtistId(id);
        Artist artist = artistDao.findById(id);
        model.addAttribute("artist_name", artist.getName());
        model.addAttribute("albums", albumsByArtist);

        return "displayAlbum";

    }


}
