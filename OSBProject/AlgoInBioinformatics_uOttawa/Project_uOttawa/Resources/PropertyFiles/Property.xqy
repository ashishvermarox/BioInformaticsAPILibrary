<Properties>
	
	<!-- These properties are used to while writing to below folder, each folder in service specific, all paths must end with '/'-->
	
	<ServiceName>Kmer_SSR</ServiceName> <!-- dont use '-' here -->
        
        <Folderproperties>
		<ArchiveZoneInput>C:/University/AlgoInBio/Project/FolderZone/kmerssr/ArchiveZone/Input/</ArchiveZoneInput>
		<ArchiveZoneOutput>C:/University/AlgoInBio/Project/FolderZone/kmerssr/ArchiveZone/Output/</ArchiveZoneOutput>
		<HttpPath>http://localhost:7001/uOttawa-Bioinformatics/FolderZone/kmerssr/ArchiveZone/Output/</HttpPath>
		<ErrorZoneInput>C:/University/AlgoInBio/Project/FolderZone/kmerssr/ErrorZone/Input/</ErrorZoneInput>
		<ProcessZone>C:/University/AlgoInBio/Project/FolderZone/kmerssr/ProcessZone/</ProcessZone>
		<Extension>.txt</Extension>
	</Folderproperties>
	
    <ExternalServiceLinks>
                <HTMLResource>http://localhost:7001/uOttawa-Bioinformatics/Html_Utilities/</HTMLResource>
                <DisplayResult>http://localhost:7003/soa/uottawa/displayresult?file=</DisplayResult>
                <ViewProcessing>http://localhost:7003/soa/uottawa/resultprocessing?</ViewProcessing>
                <RefreshInterval>5</RefreshInterval>
                <ProcessingStatusText>Processing...</ProcessingStatusText>
                <ResultViewStatusComplete>complete</ResultViewStatusComplete>
                <ResultViewStatusProcessing>processing</ResultViewStatusProcessing>
                <SleepTime>15000</SleepTime>
    </ExternalServiceLinks>
	
</Properties>