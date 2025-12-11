# Spring Boot File Upload POC - Fix for Files ≥ 3MB

## 🎯 The Problem
REST API fails to upload files ≥ 3MB because Spring Boot's default `max-file-size` is **1MB**.

## ✅ The Solution
Add 3 lines to `application.properties`:

```properties
spring.servlet.multipart.max-file-size=50MB
spring.servlet.multipart.max-request-size=100MB
server.tomcat.max-http-form-post-size=100MB
```

---

## 🚀 Quick Start

### Run the Application
```bash
mvn spring-boot:run
```

### Test with Swagger UI
1. Open: `http://localhost:8080/swagger-ui.html`
2. Click **POST /api/v1/files/upload**
3. Click **"Try it out"**
4. Select a file ≥ 3MB
5. Click **"Execute"**
6. ✅ Success!

### Test with cURL
```bash
curl -X POST http://localhost:8080/api/v1/files/upload -F "file=@your-file.pdf"
```

### Test with Postman
- **POST** `http://localhost:8080/api/v1/files/upload`
- Body → **form-data**
- Key: `file` (Type: File)
- Select file and **Send**

---

## 📡 API Endpoints

### Upload File
```
POST /api/v1/files/upload
```
**Response:**
```json
{
  "success": true,
  "fileName": "document.pdf",
  "size": 3145728,
  "sizeInMB": 3.0,
  "uploadTimestamp": "2025-12-11T12:00:00"
}
```

### Health Check
```
GET /api/v1/health
```
**Response:**
```json
{
  "status": "UP",
  "maxFileSize": "50MB",
  "maxRequestSize": "100MB"
}
```

---

## 🏭 Apply to Your Production App

**Step 1:** Add to your `application.properties`:
```properties
spring.servlet.multipart.max-file-size=50MB
spring.servlet.multipart.max-request-size=100MB
server.tomcat.max-http-form-post-size=100MB
```

**Step 2:** Restart your application

**Step 3:** Test with a 3MB+ file

**Done!** ✅

### If You Have Load Balancer/Ingress

**NGINX:**
```nginx
client_max_body_size 50M;
```

**Kubernetes Ingress:**
```yaml
annotations:
  nginx.ingress.kubernetes.io/proxy-body-size: "50m"
```

---

## 📁 Project Structure

```
src/main/java/com/example/multipart/
├── MultipartPocApplication.java          # Main app
├── controller/
│   ├── FileUploadController.java        # Upload endpoint
│   └── HealthController.java            # Health check
├── service/
│   └── FileProcessingService.java       # File processing
├── dto/
│   └── FileUploadResponse.java          # Response model
├── exception/
│   └── GlobalExceptionHandler.java      # Error handling (413, 500)
└── config/
    └── OpenAPIConfig.java               # Swagger config

src/main/resources/
└── application.properties               # ⭐ THE FIX IS HERE
```

---

## ❓ FAQ

**Q: Can I increase to 100MB or more?**  
A: Yes! Just change the values in `application.properties`

**Q: Is this the standard Spring Boot approach?**  
A: Yes! Multipart is the recommended method for file uploads

**Q: Do I need S3 or database storage?**  
A: No! This POC processes files in-memory only

**Q: What if file exceeds 50MB?**  
A: Returns HTTP 413 (Payload Too Large) with clear error message

**Q: Will this work with Spring Boot 2.x?**  
A: Yes! Just update the version in `pom.xml`

---

## 🔧 Adjust File Size Limits

For larger files, update `application.properties`:

```properties
# For 100MB files
spring.servlet.multipart.max-file-size=100MB
spring.servlet.multipart.max-request-size=200MB

# For 500MB files
spring.servlet.multipart.max-file-size=500MB
spring.servlet.multipart.max-request-size=1GB
```

---

## 📝 Summary

**Problem:** Files ≥ 3MB fail (default is 1MB)  
**Solution:** 3 configuration lines  
**Result:** Files up to 50MB work perfectly

This is the **standard, production-ready fix** for Spring Boot file upload issues.

---

**Built with:** Spring Boot 3.2.0 | Java 11+ | Maven
