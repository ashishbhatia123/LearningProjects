### ✨ MongoDB Custom Repository Flow in Spring Boot (with Code)

---

#### 🔹 1. Configuration Setup

You start by defining your MongoDB connection details in `application.yml`:

```yaml
my:
  mongo:
    uri: mongodb://localhost:27017
    database: mydb
```

---

#### 🔹 2. Reading YAML into a Java Class

You create a `MyMongoPropertiesConfig` class to bind the values from YAML:

```java
@Component
@ConfigurationProperties(prefix = "my.mongo")
public class MyMongoPropertiesConfig {
    private String uri;
    private String database;
}
```

➡ Spring reads `my.mongo.uri` and `my.mongo.database` and injects them into this config bean.

---

#### 🔹 3. Creating Beans for MongoDB

Using this config bean, you define the actual MongoDB beans:

```java
@Configuration
public class MyMongoBeanConfig {

    @Bean
    public MongoClient myMongoClient(MyMongoPropertiesConfig config) {
        return MongoClients.create(config.getUri());
    }

    @Bean
    public MongoTemplate myMongoTemplate(MongoClient client, MyMongoPropertiesConfig config) {
        return new MongoTemplate(client, config.getDatabase());
    }
}
```

➡ These are available for injection wherever needed.

---

#### 🔹 4. Custom Generic Repository Interface

A reusable generic interface:

```java
public interface MongoCustomRepository<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
}
```

---

#### 🔹 5. Custom Generic Repository Implementation

A base class implementing that interface:

```java
@Repository
@AllArgsConstructor
public class MongoCustomRepositoryImpl<T, ID> implements MongoCustomRepository<T, ID> {

    private final MongoTemplate mongoTemplate;
    private final Class<T> entityClass;

    @Override
    public T save(T entity) {
        return mongoTemplate.save(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(mongoTemplate.findById(id, entityClass));
    }

    @Override
    public List<T> findAll() {
        return mongoTemplate.findAll(entityClass);
    }
}
```

---

#### 🔹 6. Entity-Specific Repository Interface

The `UserRepository` interface:

```java
public interface UserRepository extends MongoCustomRepository<User, String> {}
```

---

#### 🔹 7. Entity-Specific Repository Implementation

You extend the generic base and pass `User.class`:

```java
@Repository
public class UserRepositoryImpl extends MongoCustomRepositoryImpl<User, String> implements UserRepository {

    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        super(mongoTemplate, User.class);
    }
}
```

---

#### 🔹 8. Service Layer

Service layer uses `UserRepository`:

```java
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(User user) { return userRepository.save(user); }
    public Optional<User> findById(String id) { return userRepository.findById(id); }
    public List<User> findAll() { return userRepository.findAll(); }
}
```

---

#### 🔹 9. Controller Layer

Expose via REST:

```java
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id) {
        return userService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }
}
```

---

### 🔺 Final Flow Summary

YAML config → binds to `@ConfigurationProperties` bean → used to create MongoDB beans (`MongoClient`, `MongoTemplate`) → injected into generic repository → extended by entity-specific repository (`UserRepositoryImpl`) → injected into service → exposed via controller.

Let me know if you'd like to add exception handling, query methods, or pagination next!
