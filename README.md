# fifty280-common

![Java CI with Gradle](https://github.com/paul-tinius/fifty280-common/workflows/Java%20CI%20with%20Gradle/badge.svg)

![GitHub version](https://img.shields.io/github/v/tag/Paul-tinius/Fifty280-common?label=version&style=plastic)

## Release

The versions have to be stored as annotated git tags in the format of [semantic versioning](https://semver.org/).

To create a new annotated release tag:

```bash
git tag -a 1.0.0-alpha.1 -m "new alpha release of version 1.0.0"
git push --tags
```

Following commits without a release tag will have the `snapshotSuffix` (default `SNAPSHOT`) appended 
and the version number bumped according to `incrementer` (default `minor`) strategy, e.g., `1.1.0-alpha.1-SNAPSHOT`.
