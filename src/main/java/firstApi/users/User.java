package firstApi.users;

import jakarta.annotation.Resource;

@Resource
public record User(long id, String name, String surname) {
}