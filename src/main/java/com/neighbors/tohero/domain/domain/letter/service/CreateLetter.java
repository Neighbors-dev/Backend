package com.neighbors.tohero.domain.domain.letter.service;

import com.neighbors.tohero.application.letter.dto.CreateLetterRequest;
import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.common.enums.TargetJob;
import com.neighbors.tohero.common.exception.address.AddressException;
import com.neighbors.tohero.common.jwt.JwtProvider;
import com.neighbors.tohero.domain.domain.address.model.Address;
import com.neighbors.tohero.domain.domain.mainPage.model.Letter;
import com.neighbors.tohero.domain.domain.user.model.User;
import com.neighbors.tohero.domain.query.AddressRepository;
import com.neighbors.tohero.domain.query.LetterRepository;
import com.neighbors.tohero.domain.query.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@DomainService
@RequiredArgsConstructor
public class CreateLetter {

    private final LetterRepository letterRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final JwtProvider jwtProvider;

    public long createLetter(long userId, String writer, CreateLetterRequest createLetterRequest){
        String recommenderEmailDividedBySlash = null;
        if(createLetterRequest.recommenderCode() != null){
            recommenderEmailDividedBySlash = jwtProvider.getRecommenderEmails(createLetterRequest.recommenderCode());
        }
        User user = userRepository.getUserAndUpdateRecommenders(repo -> repo.findByUserId(userId), recommenderEmailDividedBySlash);
        Letter newLetter;
        try{
            Address address = addressRepository.getAddressById(createLetterRequest.addressId());
            newLetter = Letter.builder()
                    .letterContent(createLetterRequest.content())
                    .isOpened(false)
                    .targetName(createLetterRequest.heroName())
                    .isPublic(createLetterRequest.isPublic())
                    .readingAlarm(createLetterRequest.readingAlarm())
                    .address(address)
                    .writer(user.getUserName())
                    .user(user)
                    .build();
        }catch(AddressException | NullPointerException e){
            newLetter = Letter.builder()
                    .letterContent(createLetterRequest.content())
                    .isOpened(false)
                    .targetName(createLetterRequest.heroName())
                    .isPublic(createLetterRequest.isPublic())
                    .readingAlarm(createLetterRequest.readingAlarm())
                    .address(null)
                    .writer(user.getUserName())
                    .user(user)
                    .build();
        }

        Letter createdLetter = letterRepository.createLetter(newLetter);
        reflectRecommendation(user, writer, createLetterRequest.recommenderCode());

        return createdLetter.getLetterId();
    }

    public long createGuestLetter(String writer, String content, TargetJob targetJob, Long addressId, String heroName, boolean isPublic, String recommenderCode){
        Letter newLetter;
        if(addressId == null){
            newLetter = Letter.builder()
                    .letterContent(content)
                    .isOpened(false)
                    .targetName(heroName)
                    .isPublic(isPublic)
                    .readingAlarm(false)
                    .address(null)
                    .writer(writer)
                    .user(null)
                    .build();
        }
        else{
            Address address = addressRepository.getAddressById(addressId);
            newLetter = Letter.builder()
                    .letterContent(content)
                    .isOpened(false)
                    .targetName(heroName)
                    .isPublic(isPublic)
                    .readingAlarm(false)
                    .address(address)
                    .writer(writer)
                    .user(null)
                    .build();
        }

        Letter createdLetter = letterRepository.createLetter(newLetter);
        //todo 비회원 편지 작성 공유하기 반영 막으려면 이거 주석처리
        reflectRecommendation(writer, recommenderCode);

        return createdLetter.getLetterId();
    }

    private void reflectRecommendation(String writer, String recommenderCode){
        if(recommenderCode != null){
            String recommenderEmailsDividedBySlash = jwtProvider.getRecommenderEmails(recommenderCode);
            List<String> recommenderEmails = List.of(recommenderEmailsDividedBySlash.split("/"));
            userRepository.reflectRecommendation(writer, recommenderEmails);
        }
    }

    private void reflectRecommendation(User user, String writer, String recommenderCode){
        if(recommenderCode != null){
            String recommenderEmailsDividedBySlash = jwtProvider.getRecommenderEmails(recommenderCode);
            List<String> recommenderEmails = List.of(recommenderEmailsDividedBySlash.split("/"));
            userRepository.reflectRecommendation(writer, recommenderEmails);
        }
    }
}
