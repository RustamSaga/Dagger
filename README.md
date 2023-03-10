# Hilt. Custom component

For ease of use, I added TODO filter. To follow links you need to create filters - click to `TODO`
in bottom panel -> icon
filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png)
-> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger`

If for some reason we are not satisfied with standard Hilt components, we can create our own. 
Usually this is necessary if you want to bind the component to a lifecycle other than
Lifecycle standard Android entities such as Activity, Fragment, etc.


