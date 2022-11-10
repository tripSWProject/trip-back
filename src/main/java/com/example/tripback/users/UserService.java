package com.example.tripback.users;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;


}
