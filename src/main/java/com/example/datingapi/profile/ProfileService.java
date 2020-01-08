package com.example.datingapi.profile;

import com.example.datingapi.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public void addProfile(Profile profile){
        profileRepository.save(profile);
    }

    public Profile fetchProfile(int id) throws Exception {
        Optional<Profile> profile = profileRepository.findById(id);
        if(profile.isPresent()) {
            return profile.get();
        } else {
            throw new Exception("No profile record exist for given id");
        }
    }

    public List<Profile> fetchAllProfiles(){
        return profileRepository.findAll();
    }

    public Profile updateProfile(Profile user_profile , int id){
        return profileRepository.findById(id)
                .map(profile -> {
                    profile.setAge(user_profile.getAge());
                    profile.setType(user_profile.getType());
                    profile.setBio(user_profile.getBio());
                    profile.setMy_type(user_profile.getMy_type());
                    profile.setHobbies(user_profile.getHobbies());
                    profile.setAge_bracket(user_profile.getAge_bracket());
                    profile.setFavorited(user_profile.getFavorited());
                    return profileRepository.save(user_profile);
                })
                .orElseGet(() -> {
                    user_profile.setId(id);
                    return profileRepository.save(user_profile);
                });
    }

    public void deleteProfile(int id) throws Exception{
        Optional<Profile> profile = profileRepository.findById(id);
        if(profile.isPresent()) {
            profileRepository.deleteById(id);
        } else {
            throw new Exception("No profile found with this Id");
        }
    }

}
