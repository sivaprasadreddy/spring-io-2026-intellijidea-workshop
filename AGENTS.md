# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot workshop project for Spring I/O 2026 demonstrating IntelliJ IDEA productivity features. 
It contains three parallel implementations of a BookStore application:

- **bookstore** — Traditional layered architecture (web → service → repository → entity)
- **bookstore-modulith-wip** — Work-in-progress Spring Modulith refactoring (workshop exercises)
- **bookstore-modulith** — Completed modular monolith with Spring Modulith

**Key stack**: Java 25, Spring Boot 4.0.5, Spring Data JPA, Spring Modulith 2.0.3, Flyway, Redis, PostgreSQL, Testcontainers, Docker Compose
