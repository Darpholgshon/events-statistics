"Title Id","Name","Category","Venue Id","Address","Major?","Next Showing","Last Showing","Level 2 Description"
<#list titles as title>
"${title.titleId}","${title.name}","${title.category}","${title.venueId}","${title.address}","${(title.major)!0}","${title.nextPerformance}","${title.lastPerformance}","${(title.level2)!'None'}"
</#list>
