# Dagger mini-tutorial

For ease of use, I added TODO filter. To follow links you need to create filters -
 click to `TODO` in bottom panel -> icon filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png) -> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger` 


### dagger.Lazy - we don’t always need this object, only in some cases. Dagger creates it as needed
### Provider - Completely similar to Lazy, but at each call get it will create a new object