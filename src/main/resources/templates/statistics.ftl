<h2>Event Related Queries Report</h2>
<p>The queries have returned the following statistics for ${today}.</p>
<table border="3px" cellpadding="3px" cellspacing="1px" width="200px">
    <tr style="background: whitesmoke;">
        <th>Type</th>
        <th>Value</th>
    </tr>
<#list statistics as stat>
    <tr>
        <td>${stat.key}</td>
        <td style="text-align: right">${stat.value}</td>
    </tr>
</#list>
</table>
<p style='color:red'>This email was generated by the event count query report service. Don not reply to this automaton's mailbox.</p>
