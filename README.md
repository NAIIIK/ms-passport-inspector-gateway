# ms-passport-inspector-gateway

BFF (backend-for-frontend) for the
[passport-inspector-platform](https://github.com/NAIIIK/passport-inspector-platform).
The single entry point for external clients — issues JWTs and forwards
authenticated requests to [ms-passport-inspector](https://github.com/NAIIIK/ms-passport-inspector)
over OpenFeign. Holds no business logic or persistence of its own.

## Stack

Java 21, Spring Boot 4.1, Spring Security (JWT issuance + validation),
OpenFeign, Prometheus/Micrometer.

## Running standalone

Requires `ms-passport-inspector` running and reachable (see the
[umbrella repo](https://github.com/NAIIIK/passport-inspector-platform) for
`docker compose up`, or point `PASSPORT_INSPECTOR_CLIENT_URL` at wherever
it's running).

```bash
./mvnw spring-boot:run
```

Default port: `8082`.

## Configuration

| Env var | Default | Purpose |
|---|---|---|
| `PASSPORT_INSPECTOR_CLIENT_URL` | `http://localhost:8080` | Core service base URL |
| `JWT_SECRET` | (demo default — override in any real deployment) | Shared HS256 secret with the core service |
| `JWT_ACCESS_TOKEN_MINUTES` | `60` | Access token validity |

Demo users are seeded in `application.yaml` under `app.security.api-users`
(`demo`/`demo`, role `CLIENT`; `admin`/`admin`, roles `CLIENT` + `ADMIN`) —
replace with a real user store before this goes anywhere beyond a demo.

## API

| Method | Path | Purpose |
|---|---|---|
| `POST` | `/api/auth/login` | Exchange username/password for a JWT |
| `POST` | `/api/passport-checks` | Start a single passport check |
| `GET` | `/api/passport-checks/{jobId}` | Poll result of a single check |
| `POST` | `/api/passport-check-batches` | Upload a CSV for batch verification |
| `GET` | `/api/passport-check-batches/{jobId}` | Poll result of a batch check |

Every endpoint except `/api/auth/login` requires a bearer JWT obtained from
login. The gateway attaches the caller's `merchantId` to downstream calls —
callers never pass it directly.

## Monitoring

`/actuator/health`, `/actuator/info`, and `/actuator/prometheus` are public
(no JWT required); all other actuator endpoints require authentication.

## Tests

```bash
./mvnw test
```