
$uri = "http://localhost:8080/orders"
$headers = @{"Content-Type" = "application/json"}
$body = '{"userId": "a1b2c3d4-e5f6-11ec-8fea-0242ac120001", "productId": "f1e2d3c4-b5a6-11ec-9fea-0242ac120001", "quantity": 1}'

1..10 | ForEach-Object {
    Start-Job -ScriptBlock {
        curl -X POST $using:uri -H "Content-Type: application/json" -d $using:body
    }
}

Get-Job | Wait-Job
Get-Job | ForEach-Object {
    Write-Host "Αποτέλεσμα από job $_:"
    Receive-Job -Job $_
}
Get-Job | Remove-Job
